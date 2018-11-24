package cc.msbootcamp.goods;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;


@AnalyzeClasses(packages = "cc.msbootcamp.goods")
class MyArchitectureTest {
    private static final JavaClasses importedClasses = new ClassFileImporter().importPackages("cc.msbootcamp.goods");

    @ArchTest
    public static final ArchRule archRule = classes().that().haveNameMatching(".*Repository")
            .should().onlyBeAccessed().byClassesThat().haveNameMatching(".*Service");

    @Test
    void should_repository_located_in_correct_package() {
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