package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.Comment;
import com.worthsoln.repository.CommentDao;
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
