package com.yhd.sql.udf.cb.sim

import java.math.BigDecimal

import org.apache.hadoop.hive.ql.exec.UDF

class CalculateSimilarity extends UDF {

  val BRAND_WEIGHT = 0.3
  val CATEGORY_WEIGHT = 0.27
  val ATTR_WEIGHT = 0.25
  val OTHER_WEIGHT = 0.18
  val SIM_THRESHOULD = 0.35
  val LENGTHTHRESHOULD = 100

  def evaluate(brandSim: Double, categorySim: Double, attributeSim: Double,
               otherSim: Double, priceSim: Double): Double = {
    val similarity = BRAND_WEIGHT * brandSim + CATEGORY_WEIGHT * categorySim +
      ATTR_WEIGHT * attributeSim + otherSim * OTHER_WEIGHT
    val tmp2Similar = new BigDecimal(similarity * priceSim)
    tmp2Similar.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()
  }
}
