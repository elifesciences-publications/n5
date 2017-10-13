/**
 * Copyright (c) 2017, Stephan Saalfeld
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.janelia.saalfeldlab.n5.s3;

import java.io.IOException;

import org.janelia.saalfeldlab.n5.N5Reader;
import org.janelia.saalfeldlab.n5.N5Writer;

import com.amazonaws.services.s3.AmazonS3;
import com.google.gson.GsonBuilder;

/**
 * Factory methods to create Amazon Web Services S3-based
 * {@link N5Reader N5Readers} and {@link N5Writer N5Writers}.
 *
 * @author Igor Pisarev
 */
public abstract class N5AmazonS3Factory {

	/**
	 * Opens an {@link N5Reader} at a given base path with a custom
	 * {@link GsonBuilder} to support custom attributes.
	 *
	 * If the base path does not exist, it will not be created and all
	 * subsequent attempts to read attributes, groups, or datasets
	 * will fail with an {@link IOException}.
	 *
	 * @param basePath n5 base path
	 * @param gsonBuilder
	 */
	public static N5Reader openS3Reader(final AmazonS3 s3, final String bucketName, final GsonBuilder gsonBuilder) {

		return new N5AmazonS3ReaderWriter(s3, bucketName, gsonBuilder);
	}

	/**
	 * Opens an {@link N5Writer} at a given base path with a custom
	 * {@link GsonBuilder} to support custom attributes.
	 *
	 * If the base path does not exist, it will not be created and all
	 * subsequent attempts to read or write attributes, groups, or datasets
	 * will fail with an {@link IOException}.
	 *
	 * If the base path is not writable, all subsequent attempts to write
	 * attributes, groups, or datasets will fail with an {@link IOException}.
	 *
	 * @param basePath n5 base path
	 * @param gsonBuilder
	 */
	public static N5Writer openS3Writer(final AmazonS3 s3, final String bucketName, final GsonBuilder gsonBuilder) {

		return new N5AmazonS3ReaderWriter(s3, bucketName, gsonBuilder);
	}

	/**
	 * Opens an {@link N5Reader} at a given base path.
	 *
	 * If the base path does not exist, it will not be created and all
	 * subsequent attempts to read or write attributes, groups, or datasets
	 * will fail with an {@link IOException}.
	 *
	 * If the base path is not writable, all subsequent attempts to write
	 * attributes, groups, or datasets will fail with an {@link IOException}.
	 *
	 * @param basePath n5 base path
	 */
	public static N5Reader openFSReader(final AmazonS3 s3, final String bucketName) {

		return new N5AmazonS3ReaderWriter(s3, bucketName);
	}

	/**
	 * Opens an {@link N5Writer} at a given base path.
	 *
	 * If the base path does not exist, it will not be created and all
	 * subsequent attempts to read or write attributes, groups, or datasets
	 * will fail with an {@link IOException}.
	 *
	 * If the base path is not writable, all subsequent attempts to write
	 * attributes, groups, or datasets will fail with an {@link IOException}.
	 *
	 * @param basePath n5 base path
	 */
	public static N5Writer openS3Writer(final AmazonS3 s3, final String bucketName) {

		return new N5AmazonS3ReaderWriter(s3, bucketName);
	}
}