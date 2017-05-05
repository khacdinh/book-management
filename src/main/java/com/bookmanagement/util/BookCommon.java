/**
 * 
 */
package com.bookmanagement.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author Dino
 */
public class BookCommon {
    public static final int PAGE_SIZE = 10;

    public static Pageable getPageRequest(Integer pageNumber) {
        return new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
    }
}
