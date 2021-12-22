package com.everest.airline.service;


public interface PricingStrategy {
    Double calculatePrice(Integer basePrice);

    static PricingStrategy defaultPrice(Integer passengerCount){
        return basePrice -> (double) (basePrice * passengerCount);
    }

    static PricingStrategy priceCategoryOne(Integer passengerCount, Integer base){
        Double extraAmount = (double) ((base * 20) / 100);
        return basePrice -> (basePrice + extraAmount)*passengerCount;
    }

    static PricingStrategy priceCategoryTwo(Integer passengerCount, Integer base){
        Double extraAmount = (double) ((base * 35) / 100);
        return basePrice -> (basePrice + extraAmount)*passengerCount;
    }

    static PricingStrategy priceCategoryThree(Integer passengerCount, Integer base){
        Double extraAmount = (double) ((base * 50) / 100);
        return basePrice -> (basePrice + extraAmount)*passengerCount;
    }


}

