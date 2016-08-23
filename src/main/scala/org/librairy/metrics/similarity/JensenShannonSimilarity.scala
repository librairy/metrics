package org.librairy.metrics.similarity

import org.librairy.metrics.distance.JensenShannonDivergence

/**
 * Created by cbadenes on 22/04/15.
 *
 * Measure of similarity based on the Jensen-Shannon Divergence between two density functions
 */
object JensenShannonSimilarity {

  def apply(p: Array[Double], q: Array[Double]): Double = {
    Math.pow(10,-JensenShannonDivergence(p,q))
  }

}
