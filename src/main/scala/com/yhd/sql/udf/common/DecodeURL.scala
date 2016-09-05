package com.yhd.sql.udf.common

import java.net.URLDecoder

import org.apache.hadoop.hive.ql.exec.UDF

class DecodeURL extends UDF {
  def evaluate(rawValue: String): String = {
    try {
      URLDecoder.decode(rawValue, "UTF-8")
    } catch {
      case e: IllegalArgumentException => decodeInvalidFormatting(rawValue)
      case e: NullPointerException => "Invalid"
    }
  }

  private def decodeInvalidFormatting(rawValue: String) = {
    val rawWithStrippedTrailing = rawValue.substring(0, rawValue.lastIndexOf("%"))
    try {
      URLDecoder.decode(rawWithStrippedTrailing, "UTF-8")
    } catch {
      case _ => "http://invalid-url-decoding.com"
    }
  }
}
