package ${apiGeneratorConfig.apiPackage};

import com.idukelu.learn.springboot.mybatis.generator.business.pojo.vo.BookVO;
import com.idukelu.learn.springboot.mybatis.generator.business.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "书籍接口")
@RestController
@RequestMapping("/book")
public class BookController {

private BookService bookService;

@Autowired
public BookController(BookService bookService) {
this.bookService = bookService;
}

@ApiOperation(value = "保存书籍")
@PostMapping("/save")
public BookVO saveBook(@RequestBody BookVO bookVO) {
return bookService.saveBook(bookVO) ;
}

@ApiOperation(value = "通过 id 删除书籍")
@DeleteMapping("/delete/{id}")
public String deleteBook(@PathVariable String id) {
return bookService.deleteBook(id);
}

@ApiOperation(value = "通过 id 更新书籍")
@PutMapping("/update/{id}")
public BookVO updateBook(@PathVariable String id, @RequestBody BookVO bookVO) {
return bookService.updateBook(id, bookVO);
}

@ApiOperation(value = "通过 id 查询书籍", nickname = "getBook")
@GetMapping("/get/{id}")
public BookVO getBook(@PathVariable String id) {
return bookService.getBook(id);
}

@ApiOperation(value = "查询书籍列表")
@GetMapping("/list")
public List<BookVO> listBook() {
    return bookService.listBook();
    }
}