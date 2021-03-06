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

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.sonar.wsclient.connectors.HttpClient4Connector;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * TODO javadoc
 *
 * @author fmueller
 */
@Slf4j
@Service
public class SonarConnectionCheckerService {

  private static final int OK = 200;

  public boolean isReachable(SonarConnectionSettings connectionSettings) {
    if (connectionSettings == null || Strings.isNullOrEmpty(connectionSettings.getUrl())) {
      return false;
    }

    HttpClient4Connector connector = new HttpClient4Connector(connectionSettings.asHostObject());
    try {
      return connector.getHttpClient()
          .execute(new HttpGet(connectionSettings.getUrl() + "/api/metrics")).getStatusLine().getStatusCode() == OK;
    } catch (IOException e) {
      log.info("Sonar is not reachable during connection check.", e);
      return false;
    }
  }
}
