package com.ssafy.comment.model;

import java.util.List;

import com.ssafy.notice.model.FileInfoDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentDto {
    private int commentNo;
    private String contentId;
    private String userName;
    private String email;
    private String content;
    private String picture;
    private String createdDate;
    private String updatedDate;
}
