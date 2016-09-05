/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yhd.sql.udf.sb.sim

import com.yhd.sql.udf.cb.sim.CalculateAutoSimilarity
import org.junit.{After, Before, Test}
import org.scalatest.junit.JUnitSuite

class CalculateAutoSimilaritySuite extends JUnitSuite {

  var cas: CalculateAutoSimilarity = _

  @Before def initialize() {
    cas = new CalculateAutoSimilarity
  }

  @Test def testIntersectionIsZore() {
    val d = cas.evaluate("磁带,收录机,tape recorder,录音机", "复读,收录,收音,微型")
    assert(0.0 === d)
  }

  @Test def testIntersectionIsNotZore() {
    val d1: Double = cas.evaluate("磁带,收录机,tape recorder,录音机", "磁带,复读,收录,收音,微型")
    assert(0.125 === d1)
  }

  @Test def testZore {
    val d1: Double = cas.evaluate("-1", "-1")
    assert(0.5 === d1)
  }

  @Test def testZore2 {
    val d1: Double = cas.evaluate("-1", "磁带,复读,收录,收音,微型")
    val d2: Double = cas.evaluate("磁带,收录机,tape recorder,录音机", "-1")
    assert(0.0 === d1)
    assert(0.0 === d2)
  }

  @After def close(): Unit = {

  }
}
