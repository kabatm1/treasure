package treasure

import io.micronaut.context.annotation.Prototype
import javax.inject.Inject
import io.micronaut.validation.Validated;

import treasure.model.TreasureMap
import treasure.validator.MatrixScopeValid

import javax.validation.Valid
import javax.validation.constraints.Negative

@Prototype
class TreasureFinder {

    @Inject
    TreasureMap map

    Object findTreasure(@MatrixScopeValid Integer startCell) {
        def visited = []

        def isTreasure = { int clueCell ->
            map.map[(clueCell/10).intValue()-1][(clueCell%10).intValue()-1] == clueCell }

        def searchTreasure
        searchTreasure = { int clueCell ->
            if(isTreasure(clueCell)) {
                visited.add(clueCell)
                return visited
            }
            if(visited.contains(clueCell)) {
                println "NO TREASURE"
                return "NO TREASURE"
            }
            visited.add(clueCell)
            def nextCell = map.map[(clueCell/10).intValue()-1][(clueCell%10).intValue()-1]
            searchTreasure(nextCell)
        }

        return searchTreasure(startCell)
    }

}