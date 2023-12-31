package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//Controller 컨트롤러 지정
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")//localhost:8080/board/write
    public String boardWriteForm(){

        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board , Model model){

        boardService.Write(board);

        model.addAttribute("message" , "글작성이완료되었습니다");
        model.addAttribute("searchUrl" , "/board/list");

        return "message";
    }
    @GetMapping("board/list")
    public String boardList(Model model){

        model.addAttribute("list" , boardService.boardlist());

        return "boardlist";
    }
    @GetMapping("board/view")
    public String boardView(Model model , Integer id){

        model.addAttribute("board", boardService.boardView(id));

        return "boardview";
    }
    @GetMapping("board/delete")
    public String boardDelete(Integer id) {
        boardService.boardDelete(id);

        return "redirect:/board/list";
    }
    @GetMapping("board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id ,
                              Model model) {
        model.addAttribute("board" , boardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id , Board board) {
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.Write(boardTemp);

        return "redirect:/board/list";

    }

//    //localhost
//    @GetMapping("/")
//    //글자를 띄워주게하는 어노테이션
//    @ResponseBody
//    public String main(){
//        return "Hello World";
//    }
}
