package edu.tallerjava.aceptacion;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicAcceptanceTest extends AcceptanceTest {

    @Test
    public void smoke() {
        final String json = this.restTemplate.getForObject(url + "/isAlive", String.class);
        assertThat(json).isEqualTo("=)");
    }

}
