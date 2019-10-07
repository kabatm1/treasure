package treasure

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Produces

import javax.inject.Inject

@Controller("/treasure")
class TreasureController {

    @Inject
    TreasureFinder treasureFinder;

    @Get("/{startCell}")
    @Produces(MediaType.APPLICATION_JSON)
    String getTreasure(@PathVariable Integer startCell) {
        return treasureFinder.findTreasure(startCell)
    }
}
