package br.com.zup.projectfinal.domain.repository

import br.com.zup.projectfinal.domain.model.Benefit
import br.com.zup.projectfinal.utils.*

class BenefitsRepository {

    fun getAllBenefits(): List<Benefit> {
        val listBenefits = mutableListOf<Benefit>()

        listBenefits.add(Benefit(image = BENEFIT_MOVIMENTE_LOGO, title = BENEFIT_MOVIMENTE))
        listBenefits.add(Benefit(image = BENEFIT_TELAVITA_LOGO, title = BENEFIT_TELAVITA))
        listBenefits.add(Benefit(image = BENEFIT_GYMPASS_LOGO, title = BENEFIT_GYMPASS))
        listBenefits.add(Benefit(image = BENEFIT_REINTEGRAR_LOGO, title = BENEFIT_REINTEGRAR))

        return listBenefits
    }
}