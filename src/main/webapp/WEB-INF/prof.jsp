<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.Prof" %>
<%@ page import="java.util.List" %>
<%@ page import="type.Grade" %>
<!DOCTYPE html>
<html>
    <jsp:include page="components/head.jsp" >
        <jsp:param name="title" value="Professeur" />
    </jsp:include>
    <body>
        <script>
            function openModal(modal, id) {
                switch (modal) {
                    case "ajout":
                        document.querySelector(".modal-ajout").style.display = "block";
                        break;
                    case "modif":
                        const elementId = '#modal-modif-' + id
                        document.querySelector(elementId).style.display = "block";
                        break;
                    default:
                        break;
                }
            }
            function closeModal(){
                const modals = document.querySelectorAll('.modal')
                modals.forEach(item=>{
                    item.style.display = 'none';
                })
            }
        </script>
        <%@include file="components/navbar.jsp" %>
        <% List<Prof> profs = (List<Prof>) request.getAttribute("profs"); %>
        <% String search = (String) request.getAttribute("search"); %>
        <div class="container">
            <div class="t-container">
                <div class="t-header">
                    <h2>PROFESSEURS:</h2>
                    <div class="t-menu">
                        <form method="get" action="">
                            <input type="text" style="width: 200px;" value="<%= search == null ? "" : search %>" name="search" placeholder="Rechercher un professeur">
                            <button type="submit">Rechercher</button>
                        </form>
                        <button onclick="openModal('ajout')">Ajouter</button>
                    </div>
                    <div class="modal modal-ajout">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4>Ajouter un professeur</h4>
                                <span class="close" onclick="closeModal()">&times;</span>
                            </div>
                            <form method="post" action="">
                                <input type="text" required name="nom" placeholder="Nom" />
                                <input type="text" name="prenom" placeholder="Prénom" />
                                <select name="grade">
                                    <option value="MAITRE_CONFERENCE">MAITRE DE CONFERENCES</option>
                                    <option value="DOCTEUR">DOCTEUR</option>
                                    <option value="DOCTEUR_HDR">DOCTEUR HDR</option>
                                    <option value="PROFESSEUR">PROFESSEUR</option>
                                    <option value="PROFESSEUR_TITULAIRE">PROFESSEUR TITULAIRE</option>
                                </select>
                                <button type="submit">Ajouter</button>
                            </form>
                        </div>
                    </div>

                </div>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Grade</th>
                        <th>Actions</th>
                    </tr>
                    <% for (Prof prof : profs) { %>
                        <jsp:include page="components/prof-component.jsp">
                            <jsp:param name="codeprof" value="<%= prof.getCodeprof() %>"/>
                            <jsp:param name="nom" value="<%= prof.getNom() %>"/>
                            <jsp:param name="prenom" value="<%= prof.getPrenom() %>"/>
                            <jsp:param name="grade" value="<%= prof.getGrade() %>"/>
                        </jsp:include>
                    <% } %>
                </table>
            </div>
        </div>
    </body>
</html>