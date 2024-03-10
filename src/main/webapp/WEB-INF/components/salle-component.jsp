<%@ page import="models.Salle" %>
<% Salle salle = new Salle(Long.parseLong(request.getParameter("codesalle")), request.getParameter("designation")); %>
<tr>
  <td><%= salle.getCodesalle() %></td>
  <td><%= salle.getDesignation() %></td>
  <td>
    <div class="actions">
      <button onclick="openModal('modif', '<%= salle.getCodesalle() %>')">Modifier</button>
      <div class="modal" id="<%= "modal-modif-" + salle.getCodesalle() %>">
        <div class="modal-content">
          <div class="modal-header">
            <h4>Modifier une salle</h4>
            <span class="close" onclick="closeModal()">&times;</span>
          </div>
          <form method="post" action="/api/salle/update">
            <input type="hidden" name="codesalle" value="<%= salle.getCodesalle() %>" />
            <input type="text" required name="designation" placeholder="Designation" value="<%= salle.getDesignation() %>" />
            <button type="submit">Modifier</button>
          </form>
        </div>
      </div>
      <form method="post" action="/api/salle/delete">
        <input type="hidden" name="codesalle" value="<%= salle.getCodesalle() %>">
        <button type="submit">Supprimer</button>
      </form>
    </div>
  </td>
</tr>
