/*
 * Copyright 2013 - 2014 Felix Müller
 *
 * This file is part of CodeQ Invest.
 *
 * CodeQ Invest is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CodeQ Invest is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CodeQ Invest.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.codeqinvest.sonar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/module-context.xml")
public class MetricCollectorServiceIntegrationTest {

  @Autowired
  private MetricCollectorService metricCollectorService;

  private final String defaultResourceKey = "commons-logging:commons-logging";
  private final String defaultSonarHost = "http://nemo.sonarqube.org";

  @Test
  public void shouldLoadCorrectMetricValueForResourceOfCommonsLoggingProjectOnNemoDemoServer() throws ResourceNotFoundException {
    /*
     * TODO this is an ugly test: it can be easily broken by an update of the Sonar demo server
     * => replace this by usage of own sonar server for int test (puppet script + vagrant)
     */
    SonarConnectionSettings connectionSettings = new SonarConnectionSettings(defaultSonarHost, defaultResourceKey);
    assertThat(metricCollectorService.collectMetricForResource(connectionSettings, defaultResourceKey, "ncloc")).isEqualTo(2618.0);
  }

  @Test
  public void shouldReturnDefaultValueWhenResourceHasNoMeasurementForGivenMetric() throws ResourceNotFoundException {
    /*
     * TODO this is an ugly test: it can be easily broken by an update of the Sonar demo server
     * => replace this by usage of own sonar server for int test (puppet script + vagrant)
     */
    SonarConnectionSettings connectionSettings = new SonarConnectionSettings(defaultSonarHost, "abc");
    assertThat(metricCollectorService.collectMetricForResource(connectionSettings, "abc", "ncloc")).isEqualTo(MetricCollectorService.DEFAULT_VALUE);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void shouldThrowResourceNotFoundExceptionWhenMetricIsNotAvailable() throws ResourceNotFoundException {
    /*
     * TODO this is an ugly test: it can be easily broken by an update of the Sonar demo server
     * => replace this by usage of own sonar server for int test (puppet script + vagrant)
     */
    SonarConnectionSettings connectionSettings = new SonarConnectionSettings(defaultSonarHost, defaultResourceKey);
    metricCollectorService.collectMetricForResource(connectionSettings, defaultResourceKey, "abc");
  }
}
