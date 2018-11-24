package cc.msbootcamp.goods;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;


class GoodsArchitectureTest {
    private static final JavaClasses importedClasses = new ClassFileImporter().importPackages("cc.msbootcamp.goods");

    @Test
    void should_repository_accessed_by_service() {
        classes().that().haveNameMatching(".*Repository")
                .should().onlyBeAccessed().byClassesThat().haveNameMatching(".*Service")
                .check(importedClasses);
    }

    @Test
    void should_service_accessed_by_service_and_controller() {
        classes().that().resideInAPackage("..service..")
                .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..")
                .check(importedClasses);
    }
}