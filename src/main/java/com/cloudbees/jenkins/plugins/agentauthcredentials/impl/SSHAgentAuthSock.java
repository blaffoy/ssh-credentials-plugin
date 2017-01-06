/*
 * The MIT License
 *
 * Copyright (c) 2011-2012, CloudBees, Inc., Stephen Connolly.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.cloudbees.jenkins.plugins.agentauthcredentials.impl;

import com.cloudbees.plugins.credentials.impl.BaseStandardCredentials;
import com.cloudbees.plugins.credentials.CredentialsScope;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import hudson.Extension;
import java.util.logging.Logger;
import org.jenkins.ui.icon.Icon;
import org.jenkins.ui.icon.IconSet;
import org.jenkins.ui.icon.IconType;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * A simple username / password for use with SSH connections.
 */
public class SSHAgentAuthSock extends BaseStandardCredentials {

    /**
     * Ensure consistent serialization.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Our logger
     */
    private static final Logger LOGGER = Logger.getLogger(SSHAgentAuthSock.class.getName());
    
    /**
     * The environment variable which resolves the path of the auth sock
     */
    private String authSockEnvVar;

    /**
     * Constructor for stapler.
     *
     * @param scope            the credentials scope
     * @param authSockEnvVar   the auth sock env var
     * @param description      the description.
     */
    @DataBoundConstructor
    public SSHAgentAuthSock(CredentialsScope scope, String id, String authSockEnvVar, String description) {
        super(scope, id, description);
        this.authSockEnvVar = authSockEnvVar;
        LOGGER.info("constructed and set AuthSockEnvVar " + authSockEnvVar);
    }

    /**
     * {@inheritDoc}
     */
    @CheckForNull
    public String getAuthSockEnvVar() {
        return authSockEnvVar;
    }

    /**
     * {@inheritDoc}
     */
    @Extension
    public static class DescriptorImpl extends BaseStandardCredentialsDescriptor {

        /**
         * {@inheritDoc}
         */
        @Override
        public String getDisplayName() {
            return Messages.SSHAgentAuthSock_DisplayName();
        }

        /**
         * {@inheritDoc}
         */
        public String getIconClassName() {
            return "icon-ssh-credentials-ssh-key";
        }

        static {
            for (String name : new String[]{
                    "ssh-key"
            }) {
                IconSet.icons.addIcon(new Icon(
                        String.format("icon-ssh-credentials-%s icon-sm", name),
                        String.format("ssh-credentials/images/16x16/%s.png", name),
                        Icon.ICON_SMALL_STYLE, IconType.PLUGIN)
                );
                IconSet.icons.addIcon(new Icon(
                        String.format("icon-ssh-credentials-%s icon-md", name),
                        String.format("ssh-credentials/images/24x24/%s.png", name),
                        Icon.ICON_MEDIUM_STYLE, IconType.PLUGIN)
                );
                IconSet.icons.addIcon(new Icon(
                        String.format("icon-ssh-credentials-%s icon-lg", name),
                        String.format("ssh-credentials/images/32x32/%s.png", name),
                        Icon.ICON_LARGE_STYLE, IconType.PLUGIN)
                );
                IconSet.icons.addIcon(new Icon(
                        String.format("icon-ssh-credentials-%s icon-xlg", name),
                        String.format("ssh-credentials/images/48x48/%s.png", name),
                        Icon.ICON_XLARGE_STYLE, IconType.PLUGIN)
                );
            }

        }
    }
}
