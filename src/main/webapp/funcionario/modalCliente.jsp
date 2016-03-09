<%-- 
    Document   : modalCliente
    Created on : 24/02/2016, 09:46:53
    Author     : Dijalma Silva <dijalmacz@gmail.com>
--%>

<!-- Modal -->
<div id="modal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h2 class="modal-title">Cliente</h2>
            </div>
            <div class="modal-body">
                <form action="../front?action=loginUser" method="post" class="form-group">
                    <div class="form-group dj-form__input">
                        <label for="cpf">CPF:</label>
                        <input class="dj-button form-control" id="cpf" name="cpf">
                    </div>
                    <br>
                    <div class="form-group dj-form__input">
                        <label for="email">Email:</label>
                        <input class="dj-button form-control" id="email" name="email">
                    </div>
                    <br><br>
                    <div class="text-right dj-button__submit">
                        <input type="submit" class="btn btn-primary btn-lg" value="Login">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>