package com.prajwal.moneymatters.config;

import com.prajwal.moneymatters.Model.ExpenseCategory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ExpenseCategoryConverter
        implements AttributeConverter<ExpenseCategory, String> {

    @Override
    public String convertToDatabaseColumn(ExpenseCategory attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public ExpenseCategory convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return ExpenseCategory.OTHERS;
        }

        try {
            return ExpenseCategory.valueOf(dbData.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            // 🔥 BAD / OLD DATA FALLS HERE
            return mapLegacyCategory(dbData);
        }
    }

    private ExpenseCategory mapLegacyCategory(String value) {
        return switch (value.trim().toLowerCase()) {
            case "food", "grocery", "groceries" -> ExpenseCategory.GROCERIES;
            case "travel", "trip" -> ExpenseCategory.TRAVEL;
            case "electronics", "gadgets" -> ExpenseCategory.ELECTRONICS;
            default -> ExpenseCategory.OTHERS;
        };
    }
}
