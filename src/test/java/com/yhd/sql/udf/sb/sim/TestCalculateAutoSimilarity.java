/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yhd.sql.udf.sb.sim;

import com.yhd.sql.udf.cb.sim.CalculateAutoSimilarity;
import junit.framework.TestCase;
import org.junit.Test;

public class TestCalculateAutoSimilarity extends TestCase{

  CalculateAutoSimilarity cas = null;

  public void setUp() throws Exception {
    cas = new CalculateAutoSimilarity();
  }

  @Test
  public void testIntersectionIsZore() throws Exception {
    double d = cas.evaluate("磁带,收录机,tape recorder,录音机", "复读,收录,收音,微型");
    assertEquals(0.0, d);
  }

  @Test
  public void testIntersectionIsNotZore() throws Exception {
    double d = cas.evaluate("磁带,收录机,tape recorder,录音机", "磁带,复读,收录,收音,微型");
    assertEquals(0.125, d);
  }

  @Test
  public void testZore() throws Exception {
    double d2 = cas.evaluate("-1", "-1");
    assertEquals(0.5, d2);
  }

  @Test
  public void testZore2() throws Exception {
    double d1 = cas.evaluate("-1", "磁带,复读,收录,收音,微型");
    double d2 = cas.evaluate("磁带,收录机,tape recorder,录音机", "-1");
    assertEquals(0.0, d1);
    assertEquals(0.0, d2);
  }

  public void tearDown() throws Exception {
    cas = null;
  }
}
