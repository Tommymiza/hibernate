<%@ page import="models.Prof" %>
<%@ page import="type.Grade" %>
<% Prof prof = new Prof(Long.parseLong(request.getParameter("codeprof")), request.getParameter("nom"), request.getParameter("prenom"), Grade.valueOf(request.getParameter("grade"))); %>
<tr>
  <td><%= prof.getCodeprof() %></td>
  <td><%= prof.getNom() %></td>
  <td><%= prof.getPrenom() %></td>
  <td><%= prof.getGrade() %></td>
  <td>
    <div class="actions">
      <button onclick="openModal('modif', '<%= prof.getCodeprof() %>')">Modifier</button>
      <div class="modal" id="<%= "modal-modif-" + prof.getCodeprof() %>">
        <div class="modal-content">
          <div class="modal-header">
            <h4>Modifier un professeur</h4>
            <span class="close" onclick="closeModal()">&times;</span>
          </div>
          <form method="post" action="/api/prof/update">
            <input type="hidden" name="codeprof" value="<%= prof.getCodeprof() %>" />
            <input type="text" required name="nom" placeholder="Nom" value="<%= prof.getNom() %>" />
            <input type="text" name="prenom" placeholder="Prénom" value="<%= prof.getPrenom() %>" />
            <select name="grade" >
              <% if(prof.getGrade().toString().equals("MAITRE_CONFERENCE")) { %>
              <option value="MAITRE_CONFERENCE" selected>MAITRE DE CONFERENCES</option>
              <% }else{ %>
              <option value="MAITRE_CONFERENCE">MAITRE DE CONFERENCES</option>
              <% } %>
              <% if(prof.getGrade().toString().equals("DOCTEUR")) { %>
              <option value="DOCTEUR" selected>DOCTEUR</option>
              <% }else{ %>
              <option value="DOCTEUR">DOCTEUR</option>
              <% } %>
              <% if(prof.getGrade().toString().equals("DOCTEUR_HDR")) { %>
              <option value="DOCTEUR_HDR" selected>DOCTEUR HDR</option>
              <% }else{ %>
              <option value="DOCTEUR_HDR">DOCTEUR HDR</option>
              <% } %>
              <% if(prof.getGrade().toString().equals("PROFESSEUR")) { %>
              <option value="PROFESSEUR" selected>PROFESSEUR</option>
              <% }else{ %>
              <option value="PROFESSEUR">PROFESSEUR</option>
              <% } %>
              <% if(prof.getGrade().toString().equals("PROFESSEUR_TITULAIRE")) { %>
              <option value="PROFESSEUR_TITULAIRE" selected>PROFESSEUR TITULAIRE</option>
              <% }else{ %>
              <option value="PROFESSEUR_TITULAIRE">PROFESSEUR TITULAIRE</option>
              <% } %>
            </select>
            <button type="submit">Modifier</button>
          </form>
        </div>
      </div>
      <form method="post" action="/api/prof/delete">
        <input type="hidden" name="codeprof" value="<%= prof.getCodeprof() %>">
        <button type="submit">Supprimer</button>
      </form>
    </div>
  </td>
</tr>
