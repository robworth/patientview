messages = {};

messages.init = function() {
    $('.js-message-form').each(function(index, el) {
        $(el).submit(function(e) {
            e.preventDefault();
            messages.sendMessage(el);
        })
    });
};

messages.getMessageHtml = function(message) {
    return $('<article class="message" id="message-' + message.id + '">' +
             '  <h4 class="author">' + message.sender.name + ' <span class="label label-inverse pull-right date">' + message.friendlyDate + '</span></h4>' +
             '  <div class="content dull">' + message.content + '</div>' +
             '</article>');
};

messages.sendMessage = function(form) {
    var $form = $(form),
        recipientIdEl = $form.find('.js-message-recipient-id'),
        contentEl = $form.find('.js-message-content'),
        errorsEl = $form.find('.js-message-errors'),
        errors = [],
        messagesEl = $('.js-messages');

    errorsEl.html('').hide();

    if (!messages.validateNumber(recipientIdEl.val())) {
        errors.push('Please select a recipient');
    }

    if (!messages.validateString(contentEl.val())) {
        errors.push('Please enter a message');
    }

    if (errors.length > 0) {
        errorsEl.html(errors.join('<br />')).show();
        return false;
    }

    $.ajax({
        type: "POST",
        url: $form.attr('action'),
        data: {
            recipientId: recipientIdEl.val(),
            content: contentEl.val()
        },
        success: function(data) {
            if (data.errors.length > 0) {
                errorsEl.html(data.errors.join('<br />')).show();
            } else {
                // if the messages are on the page then append the message else forward them onto the conversation page
                if (messagesEl.length > 0) {
                    messagesEl.append(messages.getMessageHtml(data.message));
                    recipientIdEl.val('');
                    contentEl.val('');
                } else {
                    window.location.href = '/patient/conversation.do?id=' + message.conversation.id;
                }
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            errorsEl.html(textStatus).show();
        },
        dataType: 'json'
    });
};

messages.validateString = function(s) {
    return s && s.length > 0;
};

messages.validateNumber = function(n) {
    return n > 0 && !isNaN(n);
};

// add in a dom ready to fire utils.init
$(function() {
    messages.init();
});

