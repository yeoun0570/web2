package com.ssg.web2.todo.service;

import com.ssg.web2.todo.dao.TodoDAO;
import com.ssg.web2.todo.domain.TodoVO;
import com.ssg.web2.todo.dto.TodoDTO;
import com.ssg.web2.todo.util.ModelUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public enum TodoService {
    INSTANCE;

    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService() {

        dao = new TodoDAO();   //직접 dao 주입
        modelMapper = ModelUtil.INSTANCE.get();
    }

    public void register(TodoDTO dto) throws Exception {
        TodoVO vo = modelMapper.map(dto, TodoVO.class);
        //System.out.println(vo);
        log.info(vo);

        dao.insert(vo);      //int 반환하므로 예외처리는 후에 진행

    }


    public List<TodoDTO> listAll() throws Exception {

        List<TodoVO> voList = dao.selectAllList();
        log.info(".................");
        log.info(voList);

        List<TodoDTO> dtoList = voList.stream().map(vo -> modelMapper.map(vo, TodoDTO.class)).collect(Collectors.toList());
        return dtoList;

    }


      public TodoDTO get(Long tno) throws Exception{

            log.info("tno = " + tno);
            TodoVO todoVO = dao.selectOne(tno);
            TodoDTO dto = modelMapper.map(todoVO,TodoDTO.class);

          return dto;

      }


      public void remove(Long tno) throws Exception{
           log.info(tno);
           dao.deleteOne(tno);

      }



      public void modify(TodoDTO todoDTO) throws Exception{
              log.info("todoDTO  : " + todoDTO);
              TodoVO vo = modelMapper.map(todoDTO,TodoVO.class);
              dao.updateOne(vo);

      }




// code ver 1.0
//    //글 하나를 등록하는 기능
//    public  void register(TodoDTO dto){
//        System.out.println("DEBUG..........." + dto);
//    }
//
//
//    //등록된 글 목록 반환하는 기능   10개의 TodoDTO (글) 을 만들어서 리스트 객체로 반환
//    public List<TodoDTO> getList(){
//
//        List<TodoDTO> todoDTOS = IntStream.range(0,10).mapToObj(i->{
//
//             TodoDTO dto = new TodoDTO();
//             dto.setTno((long)i);
//             dto.setTitle("Todo...."+i);
//             dto.setDueDate(LocalDate.now());
//             return dto;
//
//        }).collect(Collectors.toList());
//                       return todoDTOS;
//    }
//
//    public TodoDTO get(Long tno){
//        TodoDTO dto = new TodoDTO();
//        dto.setTno(tno);
//        dto.setTitle("Sample Todo");
//        dto.setDueDate(LocalDate.now());
//        dto.setFinished(true);
//
//        return dto;
//
//
//
//    }

}


