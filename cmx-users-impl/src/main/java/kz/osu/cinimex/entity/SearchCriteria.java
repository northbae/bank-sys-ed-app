package kz.osu.cinimex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SearchCriteria {
    private String key;
    private String operation;
    private String value;
}
