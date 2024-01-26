package com.shamsheev.wildberries.api.statistics.service

import com.shamsheev.wildberries.api.statistics.model.Income
import com.shamsheev.wildberries.api.statistics.repository.IncomeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class IncomeService(
    @Autowired
    val incomeRepository: IncomeRepository,
    @Autowired
    val productService: ProductService,
) {
    @Transactional
    fun save(income: Income) {
        income.product = productService.saveIfNotExist(income.product)
        if (!incomeRepository.existsByIncomeIdAndDateAndProduct(
                incomeId = income.incomeId,
                date = income.date,
                product = income.product
            )
        ) {
            incomeRepository.save(income)
        }
    }

    fun save(incomesList: List<Income>) {
        incomesList.forEach(::save)
    }
}