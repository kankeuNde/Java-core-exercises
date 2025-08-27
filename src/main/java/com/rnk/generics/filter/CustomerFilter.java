package com.rnk.generics.filter;

import com.rnk.generics.Customer;

public class CustomerFilter implements Specification<Customer>{
    private Customer customer;
    public CustomerFilter(Customer customer){
        this.customer = customer;
    }

    @Override
    public boolean isSatisfiedBy(Customer item) {

        boolean result = true;
        if(this.customer.getName()!= null && !this.customer.getName().isEmpty())
            result = result && customer.getName().equals(item.getName());
        if(this.customer.getCity()!= null && !this.customer.getCity().isEmpty())
            result = result && customer.getCity().equals(item.getCity());
        return result;
    }
}
