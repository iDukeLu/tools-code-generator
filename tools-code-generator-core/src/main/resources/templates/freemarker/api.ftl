<#-- 变量声明 -->
<#if config??>
    <#assign config = config>
</#if>
<#if tagName??>
    <#assign tagName = tagName>
</#if>
<#if tagDescription??>
    <#assign tagDescription = tagDescription>
</#if>
<#if paths??>
    <#assign paths = paths>
</#if>
<#-- 文件内容 -->
package ${config["apiPackage"]};

<#list gets as get>
    ${get.key!};
    ${get.value["operationId"]!};
</#list>

<#--<#list paths?keys as path>-->
<#--    ${path!};-->
<#--    <#if paths[path]??>-->
<#--        <#assign optional = paths[path]>-->
<#--        ${optional["post"]["summary"]}-->
<#--    </#if>-->
<#--</#list>-->

<#--<#if swagger??>-->
<#--    <#list map?keys as key>-->
<#--        key:${key!}-->
<#--        value:${map[key]!}-->
<#--    </#list>-->
<#--</#if>-->


<#--import com.idukelu.learn.springboot.mybatis.generator.business.pojo.vo.BookVO;-->
<#--import com.idukelu.learn.springboot.mybatis.generator.business.service.BookService;-->
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "${tagName}", description = "${tagDescription}")
@RestController
public class ${tagName} {

<#--private BookService bookService;-->

<#--@Autowired-->
<#--public BookController(BookService bookService) {-->
<#--this.bookService = bookService;-->
<#--}-->

<#--@ApiOperation(value = "保存书籍")-->
<#--@PostMapping("/save")-->
<#--public BookVO saveBook(@RequestBody BookVO bookVO) {-->
<#--return bookService.saveBook(bookVO) ;-->
<#--}-->

<#--@ApiOperation(value = "通过 id 删除书籍")-->
<#--@DeleteMapping("/delete/{id}")-->
<#--public String deleteBook(@PathVariable String id) {-->
<#--return bookService.deleteBook(id);-->
<#--}-->

<#--@ApiOperation(value = "通过 id 更新书籍")-->
<#--@PutMapping("/update/{id}")-->
<#--public BookVO updateBook(@PathVariable String id, @RequestBody BookVO bookVO) {-->
<#--return bookService.updateBook(id, bookVO);-->
<#--}-->

<#--@ApiOperation("通过 id 查询书籍")-->
<#--@GetMapping("/get/{id}")-->
<#--public BookVO getBook(@PathVariable String id) {-->
<#--return bookService.getBook(id);-->
<#--}-->

<#--@ApiOperation(value = "查询书籍列表")-->
<#--@GetMapping("/list")-->
<#--public List<BookVO> listBook() {-->
<#--    return bookService.listBook();-->
<#--    }-->
}