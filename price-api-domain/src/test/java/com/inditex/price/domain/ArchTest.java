package com.inditex.price.domain;

import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ArchTest {

    @Test
    void  domainDependency() {
        ArchRule r1 = classes()
                .that().resideInAPackage("com.inditex.price.domain..")
                .should().onlyDependOnClassesThat()
                .resideInAPackage("com.inditex.price.domain..");
    }

}
