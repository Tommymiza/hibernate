<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.Prof" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Salle" %>
<%@ page import="models.Occuper" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<jsp:include page="components/head.jsp" >
  <jsp:param name="title" value="Occupation" />
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
<% List<Occuper> occupers = (List<Occuper>) request.getAttribute("occupers"); %>
<% List<Prof> profs = (List<Prof>) request.getAttribute("profs"); %>
<% List<Salle> salles = (List<Salle>) request.getAttribute("salles"); %>
<% DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); %>
<div class="container">
  <div class="t-container">
    <div class="t-header">
      <h2>OCCUPATION:</h2>
      <div class="t-menu">
        <button onclick="openModal('ajout')">Ajouter</button>
      </div>
      <div class="modal modal-ajout">
        <div class="modal-content">
          <div class="modal-header">
            <h4>Ajouter une occupation</h4>
            <span class="close" onclick="closeModal()">&times;</span>
          </div>
          <form method="post" action="/occuper">
            <select name="codeprof" placeholder="Professeur" >
              <% for(Prof p : profs) {%>
                <option value="<%= p.getCodeprof() %>"><%= p.getNom() %> <%= p.getPrenom() %></option>
              <% } %>
            </select>
            <select name="codesalle" placeholder="Salle">
              <% for(Salle s : salles) {%>
                <option value="<%= s.getCodesalle() %>"><%= s.getDesignation() %></option>
              <% } %>
            </select>
            <input type="datetime-local" name="date" placeholder="Date" step="7200" />
            <button type="submit">Ajouter</button>
          </form>
        </div>
      </div>
    </div>
    <table>
      <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Salle</th>
        <th>Date</th>
        <th>Actions</th>
      </tr>
      <% for (Occuper occuper : occupers) { %>
        <tr>
          <td><%= occuper.getId() %></td>
          <td><%= occuper.getProfesseur().getNom() %> <%= occuper.getProfesseur().getPrenom() %></td>
          <td><%= occuper.getSalle().getDesignation() %></td>
          <td><%= occuper.getDate().format(formatter) %></td>
          <td>
            <div class="actions">
              <button onclick="openModal('modif', '<%= occuper.getId() %>')">Modifier</button>
              <div class="modal" id="<%= "modal-modif-" + occuper.getId() %>">
                <div class="modal-content">
                  <div class="modal-header">
                    <h4>Modifier une occupation</h4>
                    <span class="close" onclick="closeModal()">&times;</span>
                  </div>
                  <form method="post" action="/api/occuper/update">
                    <input type="hidden" name="id" value="<%= occuper.getId() %>">
                    <select name="codeprof" placeholder="Professeur">
                      <% for(Prof p : profs) {%>
                        <% if(p.getCodeprof() == occuper.getProfesseur().getCodeprof()) { %>
                          <option value="<%= p.getCodeprof() %>" selected><%= p.getNom() %> <%= p.getPrenom() %></option>
                        <% }else{ %>
                          <option value="<%= p.getCodeprof() %>"><%= p.getNom() %> <%= p.getPrenom() %></option>
                        <% } %>
                      <% } %>
                    </select>
                    <select name="codesalle" placeholder="Salle">
                      <% for(Salle s : salles) {%>
                        <% if(s.getCodesalle() == occuper.getSalle().getCodesalle()) { %>
                          <option value="<%= s.getCodesalle() %>" selected><%= s.getDesignation() %></option>
                        <% }else{ %>
                          <option value="<%= s.getCodesalle() %>"><%= s.getDesignation() %></option>
                        <% } %>
                      <% } %>
                    </select>
                    <input type="datetime-local" name="date" value="<%= occuper.getDate() %>" placeholder="Date" step="7200" />
                    <button type="submit">Modifier</button>
                  </form>
                </div>
              </div>
              <form method="post" action="/api/occuper/delete">
                <input type="hidden" name="id" value="<%= occuper.getId() %>">
                <button type="submit">Supprimer</button>
              </form>
            </div>
          </td>
        </tr>
      <% } %>
    </table>
  </div>
</div>
</body>
</html>