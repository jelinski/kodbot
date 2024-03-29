<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-xs-4">
    <iframe title="Facebook widget" src="//www.facebook.com/plugins/like.php?href=https%3A%2F%2Fwww.facebook.com%2Fkodbot&amp;width&amp;layout=button_count&amp;action=like&amp;show_faces=false&amp;share=true&amp;height=21&amp;appId=663978547017773"
            scrolling="no"
            frameborder="0" style="border: none; overflow: hidden; height: 21px;" allowTransparency="true"></iframe>
</div>

<div class="col-xs-8" style="text-align: right">
    <footer>
        <strong>
            <spring:message code="web.footer.terms_of_use.label"/>
            <a href='<c:url value="/terms_of_use" />'>
                <spring:message code="web.footer.terms_of_use.link"/>
            </a>
        </strong>
        .
        <a href="https://www.linkedin.com/in/krzysztof-jelinski">
            <span itemprop="author" itemscope itemtype="http://schema.org/Person">
                <span itemprop="name">Krzysztof Jeliński</span>
            </span>
        </a>
        Toruń 2014.
        <span>
			<spring:message code="web.footer.copyright"/>©
		</span>
    </footer>
</div>
<div class="col-xs-12 text-right text-muted">
    version: <span id="version-span"></span>
    <script>
        $(function(){
            $.get("/version")
                .done(function(data){
                    var time = (new Date(data.commitTime)).toLocaleDateString();
                    var revision = data.revision;
                    $("#version-span").text(time + " (" + revision + ")");
                })
        });
    </script>
</div>
