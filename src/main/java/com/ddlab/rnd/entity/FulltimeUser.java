package com.ddlab.rnd.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class FulltimeUser implements User {

	private long id;
    private String fullName;
    private String cityName;
    private List<String> phoneNos;
}
