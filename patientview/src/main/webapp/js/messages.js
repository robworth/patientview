messages = {};

messages.init = function() {
    var messageModal = $('#messageModal');

    // loop through any message forms on set submit
    $('.js-message-form').each(function(index, el) {
        $(el).submit(function(e) {
            e.preventDefault();
            messages.sendMessage(el);
        })
    });

    // set up the modal view
    if (messageModal.length > 0) {
        messageModal.modal({
            show: false
        }).on('hidden', function () {
                var recipientIdEl = messageModal.find('.js-message-recipient-id'),
                    contentEl = messageModal.find('.js-message-content'),
                    errorsEl = messageModal.find('.js-message-errors');

                recipientIdEl.val('');
                contentEl.val('');
                errorsEl.html('').hide();
            });
    }
};

messages.getMessageHtml = function(message) {
    return $('<article class="message" id="message-' + message.id + '">' +
             '  <h4 class="author">' + message.sender.name + ' <span class="label label-inverse pull-right date">' + message.friendlyDate + '</span></h4>' +
             '  <div class="content dull">' + message.content + '</div>' +
             '</article>');
};

messages.sendMessage = function(form) {
    var $form = $(form),
        submitBtn = $form.find('.js-message-submit-btn'),
        originalBtnValue = submitBtn.val(),
        recipientIdEl = $form.find('.js-message-recipient-id'),
        conversationIdEl = $form.find('.js-message-conversation-id'),
        contentEl = $form.find('.js-message-content'),
        subjectEl = $form.find('.js-message-subject'),
        redirectEl = $form.find('.js-message-redirect'),
        errorsEl = $form.find('.js-message-errors'),
        errors = [],
        messagesEl = $('.js-messages'),
        data = {},
        onError = function(errorSt) {
            errorsEl.html(errorSt).show();
            submitBtn.val(originalBtnValue);
        };

    errorsEl.html('').hide();

    submitBtn.val('Sending...');

    // if no convo el then its a new convo
    if (conversationIdEl.length === 0) {
        if (!messages.validateNumber(recipientIdEl.val())) {
            errors.push('Please select a recipient');
        }

        if (!messages.validateString(subjectEl.val())) {
            errors.push('Please enter a subject');
        }
    } else {
        if (!messages.validateNumber(conversationIdEl.val())) {
            errors.push('Invalid conversation');
        }
    }

    if (!messages.validateString(contentEl.val())) {
        errors.push('Please enter a message');
    }

    if (errors.length > 0) {
        onError(errors.join('<br />'));
        return false;
    } else {
        data.content = contentEl.val();

        if (conversationIdEl.length === 0) {
            data.recipientId = recipientIdEl.val();
            data.subject = subjectEl.val();
        } else {
            data.conversationId = conversationIdEl.val()
        }

        $.ajax({
            type: "POST",
            url: $form.attr('action'),
            data: data,
            success: function(data) {
                if (data.errors.length > 0) {
                   onError(data.errors.join('<br />'));
                } else {
                    submitBtn.val(originalBtnValue);

                    // if the messages are on the page then append the message else forward them onto the conversation page
                    if (messagesEl.length > 0) {
                        messagesEl.append(messages.getMessageHtml(data.message));
                        contentEl.val('');
                    } else {
                        window.location.href = redirectEl.val() + '?conversationId=' + data.message.conversation.id + '#response';
                    }
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                onError(textStatus);
            },
            dataType: 'json'
        });
    }
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

