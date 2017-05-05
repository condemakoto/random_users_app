package com.kun.randomusers;

import com.kun.randomusers.domain.model.UsersListPage;
import com.kun.randomusers.interactor.TestUsersInteractor;
import com.kun.randomusers.presenter.UserPresenter;
import com.kun.randomusers.presenter.view.UsersListView;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;


/**
 * @Author Julio Kun
 * @version 0.1
 */

public class TestUserListsPresenter {

    private UserPresenter presenter;
    private UsersListView view;
    private TestUsersInteractor interactor;

    @Before
    public void config() {
        view = mock(UsersListView.class);
        interactor = new TestUsersInteractor(null);
        presenter = new UserPresenter(interactor);
    }

    @Test
    public void testPresenterWithNullScreen() {
        presenter.addView(view);
        presenter.onCreate();
        presenter.setPreviousState(null);
        presenter.onStart();

        verify(view, times(1)).showProgress();
        verify(view, times(0)).hideProgress();

        interactor.responseWithNullList();

        verify(view, times(1)).hideProgress();
        verify(view, times(1)).showEmptyScreen();

    }

    @Test
    public void testPresenterWithEmptyScreen() {
        presenter.addView(view);
        presenter.onCreate();
        presenter.setPreviousState(null);
        presenter.onStart();

        verify(view, times(1)).showProgress();
        verify(view, times(0)).hideProgress();

        interactor.responseWithEmptyList();

        verify(view, times(1)).hideProgress();
        verify(view, times(1)).showEmptyScreen();

    }

    @Test
    public void testPresenterWithUsers() {
        presenter.addView(view);
        presenter.onCreate();
        presenter.setPreviousState(null);
        presenter.onStart();

        verify(view, times(1)).showProgress();
        verify(view, times(0)).hideProgress();

        interactor.responseWithOneUser();

        verify(view, times(1)).hideProgress();
        verify(view, times(0)).showEmptyScreen();
        verify(view, times(1)).showUsers(any(UsersListPage.class));
    }

}
