package cc.msbootcamp.goods;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;


class GoodsArchitectureTest {
    private static final JavaClasses importedClasses = new ClassFileImporter().importPackages("cc.msbootcamp.goods");

    @Test
    void should_repository_accessed_by_service() {
        classes().that().haveNameMatching(".*Repository")
                .should().onlyBeAccessed().byClassesThat().haveNameMatching(".*Service")
                .check(importedClasses);
    }

    @Test
    void should_not_repository_dependent_on_service() {
        noClasses().that().resideInAnyPackage("..domain..")
                .should().dependOnClassesThat().resideInAnyPackage("..service..")
                .check(importedClasses);
    }

    @Test
    void should_service_accessed_by_service_and_controller() {
        classes().that().resideInAPackage("..service..")
                .should().onlyHaveDependentClassesThat().resideInAnyPackage("..controller..", "..service..")
                .check(importedClasses);
    }

    @Test
    void should_service_reside_in_service_package() {
        classes().that().haveSimpleNameEndingWith("Service")
                .should().resideInAnyPackage("..service..")
                .check(importedClasses);
    }

    @Test
    void should_controller_reside_in_controller_package() {
        classes().that().haveNameMatching(".*Controller")
                .should().resideInAnyPackage("..controller..")
                .check(importedClasses);
    }

    @Test
    void should_layer_composite_correctly() {
        layeredArchitecture()
                .layer("Controller").definedBy("..controller..")
                .layer("Service").definedBy("..service..")
                .layer("Persistence").definedBy("..persistence..")

                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Service")
                .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service")
                .check(importedClasses);
    }

    @Test
    void should_not_cycle_depends() {
        slices().matching("cc.msbootcamp.goods.(*)..").should().beFreeOfCycles().check(importedClasses);
    }
}