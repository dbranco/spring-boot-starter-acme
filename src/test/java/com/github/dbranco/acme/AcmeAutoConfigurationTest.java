package com.github.dbranco.acme;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import com.github.dbranco.acme.AcmeAutoConfiguration;
import com.github.dbranco.acme.AcmeEncryptRunner;
import com.github.dbranco.acme.CertGenerator;
import com.github.dbranco.acme.ChallengeController;
import com.github.dbranco.acme.ChallengeStore;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link AcmeAutoConfiguration}
 *
 * @author Christian Sueiras
 */
class AcmeAutoConfigurationTest {
    private ApplicationContextRunner contextRunner;

    @BeforeEach
    void setUp() {
        contextRunner = new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(AcmeAutoConfiguration.class));
    }

    @Test
    void defaultNotEnabled() {
        contextRunner.run(context -> {
            assertThat(context).hasNotFailed();
            assertThat(context).doesNotHaveBean(ChallengeStore.class);
        });
    }

    @Test
    void onEnableCreatesAcmeComponents() {
        contextRunner.withPropertyValues("acme.enabled=true")
                .run(context -> {
                    assertThat(context).hasNotFailed();
                    assertThat(context).hasSingleBean(ChallengeStore.class);
                    assertThat(context).hasSingleBean(ChallengeController.class);
                    assertThat(context).hasSingleBean(CertGenerator.class);
                    assertThat(context).hasSingleBean(AcmeEncryptRunner.class);
                });
    }
}