/*
 * Copyright (c) 2017. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.metrics.similarity

import org.librairy.metrics.distance.{HellingerDistance, JensenShannonDivergence}

/**
 * Created by cbadenes on 22/04/15.
 *
 * Measure of similarity based on the Jensen-Shannon Divergence between two density functions
 */
object HellingerSimilarity {

  def apply(p: Array[Double], q: Array[Double]): Double = {
    1-HellingerDistance(p,q)
  }

}
