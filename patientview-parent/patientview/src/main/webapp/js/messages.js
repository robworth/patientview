/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

messages = {};

messages.init = function() {
    var messageModal = $('#messageModal');

    // loop through any message forms on set submit
    $('.js-message-form').each(function(index, el) {
        var $form = $(el),
            unitCodeEl = $form.find('.js-message-unit-code');

        $form.submit(function(e) {
            e.preventDefault();
            messages.sendMessage(el);
        });

        // if there was a unit code el add a change event
        if (unitCodeEl.length > 0) {
            unitCodeEl.change(function() {
                messages.getRecipientsByUnit($form);
            });
        }
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
             '  <div class="content dull">' + message.formattedContent + '</div>' +
             '</article>');
};

messages.getRecipientsByUnit = function(form) {
    var $form = $(form),
        unitCodeEl = $form.find('.js-message-unit-code'),
        recipientContainer = $form.find('.js-recipient-container'),
        recipientIdEl = $form.find('.js-message-recipient-id'),
        loadingEl = $form.find('.js-message-unit-loading'),
        errorsEl = $form.find('.js-message-unit-recipient-errors');

    errorsEl.html('').hide();
    recipientContainer.hide();
    recipientIdEl.html('');

    if (messages.validateString(unitCodeEl.val())) {
        loadingEl.show();

        $.ajax({
            url: '/unit-recipients.do?unitCode=' + unitCodeEl.val(),
            success: function(html) {
                recipientIdEl.html(html);

                if (recipientIdEl.children().length <= 1) {
                    errorsEl.html('No recipients found in unit').show();
                } else {
                    recipientContainer.show();
                }

                loadingEl.hide();
            },
            error: function() {
                errorsEl.html('Error retrieving recipients for unit').show();
                loadingEl.hide();
            }
        });
    }
};

messages.sendMessage = function(form) {
    var $form = $(form),
        submitBtn = $form.find('.js-message-submit-btn'),
        originalBtnValue = submitBtn.val(),
        recipientIdEl = $form.find('.js-message-recipient-id'),
        unitCodeEl = $form.find('.js-message-unit-code'),
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
        if (!messages.validateRecipient(recipientIdEl.val())) {
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
            data.unitCode = unitCodeEl.val();
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
                        if (recipientIdEl.val() == "allAdmins" || recipientIdEl.val() == "allPatients" || recipientIdEl.val() == "allStaff") {
                            window.location.href = "/control/messaging/message_confirm.jsp"
                        } else {
                            window.location.href = redirectEl.val() + '?conversationId=' + data.message.conversation.id + '#response';
                        }
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

messages.validateRecipient = function(n) {
    return n != null && n != "";
};

// add in a dom ready to fire utils.init
$(function() {
    messages.init();
});

