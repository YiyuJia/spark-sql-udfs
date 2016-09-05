package com.yhd.sql.udf.cb.sim

import java.math.BigDecimal

import org.apache.hadoop.hive.ql.exec.UDF
import org.apache.hadoop.io.Text

class CalculateAutoSimilarity extends UDF {
  def evaluate(tempStr1: String, tempStr2: String): Double = {
    var retValue: Double = 0
    if (tempStr1.equals("-1") && tempStr2.equals("-1")) {
      retValue = 0.5
    } else if (tempStr1.equals("-1") || tempStr2.equals("-1")) {
      retValue = 0.0
    } else {
      val tempStr1Set = tempStr1.split(",").toSet
      val tempStr2Set = tempStr2.split(",").toSet
      val retain = tempStr1Set.intersect(tempStr2Set)
      val union = tempStr1Set.union(tempStr2Set)
      val similarityLast = if (union.nonEmpty) retain.size.toDouble / union.size.toDouble else 0.0
      val tmp2Similar = new BigDecimal(similarityLast)
      tmp2Similar.setScale(5, BigDecimal.ROUND_HALF_UP)
      retValue = tmp2Similar.doubleValue()
    }
    retValue
  }
}