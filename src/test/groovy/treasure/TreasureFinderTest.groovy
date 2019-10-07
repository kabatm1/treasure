package io.micronaut.test.spock

import io.micronaut.test.annotation.MicronautTest
import spock.lang.*
import javax.inject.Inject

import treasure.TreasureFinder

import javax.validation.ConstraintViolationException

@MicronautTest
class TreasureFinderTest extends Specification {

    @Inject
    TreasureFinder treasureFinder;

    @Unroll
    void "should find treasure when starting at #num"() {
        when:
        def result = treasureFinder.findTreasure(num)

        then:
        result.toString() == expected

        where:
        num | expected
        11   | "[11, 55, 15, 21, 44, 32, 13, 25, 43]"
        15   | "[15, 21, 44, 32, 13, 25, 43]"
        55   | "[55, 15, 21, 44, 32, 13, 25, 43]"
    }

    @Unroll
    void "should result no treasure when starting at #num"() {
        when:
        def result = treasureFinder.findTreasure(num)

        then:
        result.toString() == expected

        where:
        num | expected
        22   | "NO TREASURE"
        33   | "NO TREASURE"
        41   | "NO TREASURE"
    }

    @Unroll
    void "should throw exception while ouf of matrix"() {
        when:
        def result = treasureFinder.findTreasure(num)

        then:
        thrown(expectedException)

        where:
        num | expectedException
        66   | ConstraintViolationException
        00   | ConstraintViolationException
        17   | ConstraintViolationException
    }

}
