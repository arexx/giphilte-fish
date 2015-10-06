package com.x13n.giphiltefish.net.giphy.model;

/**
 * An object representing the part of a GiphyResponse that details the total number of items that
 * the API can return, and the offset of the items returned in this particular response.
 *
 * The object exists for GSON's convenience. Actual access to these properties is via the
 * GiphyResponse object.
 *
 * Created by alex on 05/10/15.
 */
class Pagination {
    int count;
    int offset;
}
