<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.Prof" %>
<%@ page import="java.util.List" %>
<%@ page import="type.Grade" %>
<%@ page import="models.Salle" %>
<!DOCTYPE html>
<html>
<jsp:include page="components/head.jsp" >
  <jsp:param name="title" value="Salle" />
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
<% List<Salle> salles = (List<Salle>) request.getAttribute("salles"); %>
<div class="container">
  <div class="t-container">
    <div class="t-header">
      <h2>SALLES:</h2>
      <div class="t-menu">
        <button onclick="openModal('ajout')">Ajouter</button>
      </div>
      <div class="modal modal-ajout">
        <div class="modal-content">
          <div class="modal-header">
            <h4>Ajouter une salle</h4>
            <span class="close" onclick="closeModal()">&times;</span>
          </div>
          <form method="post" action="/salle">
            <input type="text" name="designation" required placeholder="Designation" />
            <button type="submit">Ajouter</button>
          </form>
        </div>
      </div>
    </div>
    <table>
      <tr>
        <th>ID</th>
        <th>Designation</th>
        <th>Actions</th>
      </tr>
      <% for (Salle salle : salles) { %>
      <jsp:include page="components/salle-component.jsp">
        <jsp:param name="codesalle" value="<%= salle.getCodesalle() %>"/>
        <jsp:param name="designation" value="<%= salle.getDesignation() %>"/>
      </jsp:include>
      <% } %>
    </table>
  </div>
</div>
</body>
</html>