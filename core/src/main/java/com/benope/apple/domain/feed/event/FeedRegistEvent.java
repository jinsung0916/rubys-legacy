package com.benope.apple.domain.feed.event;

import com.benope.apple.config.domain.BenopeEvent;
import com.benope.apple.domain.feed.bean.Feed;

public class FeedRegistEvent extends BenopeEvent<Feed> {
    public FeedRegistEvent(Feed source) {
        super(source);
    }
}
