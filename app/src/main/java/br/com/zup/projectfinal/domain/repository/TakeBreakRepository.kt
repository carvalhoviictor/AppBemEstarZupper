package br.com.zup.projectfinal.domain.repository

import br.com.zup.projectfinal.domain.model.TakeBreakModel
import br.com.zup.projectfinal.utils.*

class TakeBreakRepository {

    fun getTakeBreakList(): List<TakeBreakModel> {
        val takeBreakList = mutableListOf<TakeBreakModel>()

        takeBreakList.add(TakeBreakModel(image = TAKEABREAK_NOREUNION_LOGO, title = TAKEABREAK_NOREUNION, subtitle = TAKEABREAK_NOREUNION_SUBTITLE))
        takeBreakList.add(TakeBreakModel(image = TAKEABREAK_LAUNCH_LOGO, title = TAKEABREAK_LAUNCH, subtitle = TAKEABREAK_LAUNCH_SUBTITLE))
        takeBreakList.add(TakeBreakModel(image = TAKEABREAK_TIMEREUNION_LOGO, title = TAKEABREAK_TIMEREUNION, subtitle = TAKEABREAK_TIMEREUNION_SUBTITLE))
        return takeBreakList
    }
}