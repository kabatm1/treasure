map = [[55, 14, 25, 52, 21],
           [44, 31, 11, 53, 43],
           [24, 13, 45, 12, 34],
           [42, 22, 43, 32, 41],
           [51, 23, 33, 54, 15]]

visited = []

isTreasure = { int clueCell ->
    map[(clueCell/10).intValue()-1][(clueCell%10).intValue()-1] == clueCell }

searchTreasure = { int clueCell ->
    if(isTreasure(clueCell)) {
        visited.add(clueCell)
        visited.each {println(it)}
        return visited
    }
    if(visited.contains(clueCell)) {
        println "NO TREASURE"
        return "NO TREASURE"
    }
    visited.add(clueCell)
    nextCell = map[(clueCell/10).intValue()-1][(clueCell%10).intValue()-1]
    searchTreasure(nextCell)
}

searchTreasure(11)