package ssi2.moontiw_announcement.utils;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ssi2.moontiw_announcement.dtos.PageDTO;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListMapper {
    private static final ListMapper listMapper = new ListMapper();
    private ListMapper() { }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass, ModelMapper modelMapper) {
        return source.stream().map(entity -> modelMapper.map(entity, targetClass))
                .collect(Collectors.toList());
    }

    public static ListMapper getInstance() {
        return listMapper;
    }


    public <S, T> PageDTO<T> toPageDTO(Page<S> source, Class<T> targetClass,
                                       ModelMapper modelMapper) {
        PageDTO<T> page = modelMapper.map(source, PageDTO.class);
        page.setContent(mapList(source.getContent(), targetClass, modelMapper));
        return page;
    }

    public <T> Page<T> convertToPage(List<T> objectList, Pageable pageable){
        int start = (int) pageable.getOffset();
        int end = Math.min(start+pageable.getPageSize(),objectList.size());
        List<T> subList = start>=end?new ArrayList<>():objectList.subList(start,end);
        return new PageImpl<>(subList,pageable,objectList.size());
    }
}
