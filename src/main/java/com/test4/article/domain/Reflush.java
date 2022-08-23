package com.test4.article.domain;

import com.test4.article.Dto.FlushDto;
import lombok.*;

import javax.persistence.*;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reflush")
public class Reflush {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "keyname", length = 50)
    private String keys;

    @Column(name = "val1")
    private String val1;


    public Reflush(FlushDto flushDto){
        this.keys = flushDto.getKey();
        this.val1 = flushDto.getValue();
    }
}
