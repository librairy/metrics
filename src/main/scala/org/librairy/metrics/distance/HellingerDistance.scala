/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.distance

/**
 * Created by cbadenes on 22/04/15.
 */
object HellingerDistance {

  def apply (p: Array[Double], q: Array[Double]): Double ={
    var sum : Double = 0.0

    if (p.length != q.length) return -1;

    for (i <- Range(0, p.length)) {
      sum += Math.pow(Math.sqrt(p(i)) - Math.sqrt(q(i)),2)
    }
    (1 / Math.sqrt(2))*Math.sqrt(sum)
  }

}
