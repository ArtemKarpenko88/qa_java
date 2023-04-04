package com.example;

import java.util.List;

public interface IFeline extends Predator {
    int getKittens();

    List<String> getFood() throws Exception;
}
