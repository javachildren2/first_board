package Healthlog.domain;

import java.util.Date;
import java.util.Objects;

public class BoardDto {
    private Integer bno;
    private String  title;
    private String  content;
    private String  writer;
    private Date reg_date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDto = (BoardDto) o;
        return Objects.equals(bno, boardDto.bno) && Objects.equals(title, boardDto.title) && Objects.equals(content, boardDto.content) && Objects.equals(writer, boardDto.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bno, title, content, writer);
    }
    public BoardDto() { this("","",""); }
    public BoardDto(String title, String content, String writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
    public Integer getBno() {
        return bno;
    }
    public void setBno(Integer bno) {
        this.bno = bno;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public Date getReg_date() {
        return reg_date;
    }
    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", reg_date=" + reg_date +
                '}';
    }
}
