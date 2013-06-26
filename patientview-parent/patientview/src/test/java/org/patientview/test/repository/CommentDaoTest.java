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

package org.patientview.test.repository;

import org.patientview.patientview.model.Comment;
import org.patientview.repository.CommentDao;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CommentDaoTest extends BaseDaoTest {

    @Inject
    private CommentDao commentDao;

    @Test
    public void testAddGetComment() throws Exception {
        Comment comment = new Comment();
        comment.setDatestamp("2012-01-24 11:24:06");
        comment.setBody("Test body");
        comment.setNhsno("123456789");

        commentDao.save(comment);

        assertTrue("Invalid id for new comment", comment.getId() > 0);

        Comment checkComment = commentDao.get(comment.getId());

        assertNotNull(checkComment);
        assertEquals("Comment body not persisted", comment.getBody(), checkComment.getBody());
        assertEquals("Comment datestamp not persisted", comment.getDatestamp(), checkComment.getDatestamp());
        assertEquals("Comment nhsno not persisted", comment.getNhsno(), checkComment.getNhsno());
    }

    @Test
    public void testGetComments() throws Exception {
        Comment comment1 = new Comment();
        comment1.setDatestamp("2012-01-24 11:24:06");
        comment1.setBody("Comment 1");
        comment1.setNhsno("123456789");

        commentDao.save(comment1);
        assertTrue("Invalid id for comment 1", comment1.getId() > 0);

        Comment comment2 = new Comment();
        comment2.setDatestamp("2012-01-24 11:24:06");
        comment2.setBody("Comment 2");
        comment2.setNhsno("123456789");

        commentDao.save(comment2);
        assertTrue("Invalid id for comment 2", comment2.getId() > 0);

        // create a third with a different nhsnumber which should not come back
        Comment comment3 = new Comment();
        comment3.setDatestamp("2012-01-24 11:24:06");
        comment3.setBody("Comment 3");
        comment3.setNhsno("123456788");

        commentDao.save(comment3);
        assertTrue("Invalid id for comment 3", comment3.getId() > 0);

        List<Comment> checkComments = commentDao.get("123456789");

        assertNotNull(checkComments);
        assertTrue("No comments found", !checkComments.isEmpty() && checkComments.size() > 0);
        assertTrue("To many comments found", checkComments.size() == 2);
    }
}
