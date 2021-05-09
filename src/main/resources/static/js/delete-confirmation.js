{
    "use strict";
    const deleteBtn = document.querySelector('#delete-btn');
    const deleteForm = document.querySelector('#delete-post-form');
    deleteBtn.addEventListener('click', e => {
        e.preventDefault();
        const willDelete = confirm("Are you sure you want to delete this post?");
        if (willDelete) {
            deleteForm.submit();
        }
    });
}