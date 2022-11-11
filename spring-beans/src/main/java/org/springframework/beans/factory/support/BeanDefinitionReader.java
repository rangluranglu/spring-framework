/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory.support;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.Nullable;

/**
 * Simple interface for bean definition readers that specifies load methods with
 * {@link Resource} and {@link String} location parameters.
 *
 * bean 定义阅读器的简单接口，它使用 {@link Resource} 和 {@link String} 位置参数指定加载方法。
 *
 * <p>Concrete bean definition readers can of course add additional
 * load and register methods for bean definitions, specific to
 * their bean definition format.
 *
 * <p>具体的 bean 定义阅读器当然可以为 bean 定义添加额外的加载和注册方法，具体到它们的 bean 定义格式。
 *
 * <p>Note that a bean definition reader does not have to implement
 *  * this interface. It only serves as a suggestion for bean definition
 *  * readers that want to follow standard naming conventions.
 *
 *  <p>请注意，bean 定义阅读器不必实现此接口。它仅作为对希望遵循标准命名约定的 bean 定义读者的建议。
 *
 * @author Juergen Hoeller
 * @since 1.1
 * @see org.springframework.core.io.Resource
 */
public interface BeanDefinitionReader {

	/**
	 * Return the bean factory to register the bean definitions with.
	 * <p>The factory is exposed through the {@link BeanDefinitionRegistry} interface,
	 * encapsulating the methods that are relevant for bean definition handling.
	 *
	 * 返回 bean 工厂以注册 bean 定义。
	 * <p>工厂通过 {@link BeanDefinitionRegistry} 接口公开，封装了与 bean 定义处理相关的方法。
	 */
	BeanDefinitionRegistry getRegistry();

	/**
	 * Return the {@link ResourceLoader} to use for resource locations.
	 * <p>Can be checked for the {@code ResourcePatternResolver} interface and cast
	 * accordingly, for loading multiple resources for a given resource pattern.
	 * <p>A {@code null} return value suggests that absolute resource loading
	 * is not available for this bean definition reader.
	 * <p>This is mainly meant to be used for importing further resources
	 * from within a bean definition resource, for example via the "import"
	 * tag in XML bean definitions. It is recommended, however, to apply
	 * such imports relative to the defining resource; only explicit full
	 * resource locations will trigger absolute path based resource loading.
	 * <p>There is also a {@code loadBeanDefinitions(String)} method available,
	 * for loading bean definitions from a resource location (or location pattern).
	 * This is a convenience to avoid explicit {@code ResourceLoader} handling.
	 *
	 * 返回 {@link ResourceLoader} 以用于资源定位。
	 * <p>可以检查 {@code ResourcePatternResolver} 接口并进行相应的转换，以便为给定的资源模式加载多个资源。
	 * <p>{@code null} 返回值表明绝对资源加载不适用于此 bean 定义阅读器。
	 * <p>这主要用于从 bean 定义资源中导入更多资源，例如通过 XML bean 定义中的“import”标签。但是，建议相对于定义资源应用此类导入；只有明确的完整资源位置才会触发基于绝对路径的资源加载。
	 * @see #loadBeanDefinitions(String)
	 * @see org.springframework.core.io.support.ResourcePatternResolver
	 */
	@Nullable
	ResourceLoader getResourceLoader();

	/**
	 * Return the class loader to use for bean classes.
	 * <p>{@code null} suggests to not load bean classes eagerly
	 * but rather to just register bean definitions with class names,
	 * with the corresponding classes to be resolved later (or never).
	 *
	 * 返回用于 bean 类的类加载器。
	 */
	@Nullable
	ClassLoader getBeanClassLoader();

	/**
	 * Return the {@link BeanNameGenerator} to use for anonymous beans
	 * (without explicit bean name specified).
	 *
	 * 返回 {@link BeanNameGenerator} 以用于匿名 bean（未指定显式 bean 名称）。
	 */
	BeanNameGenerator getBeanNameGenerator();


	/**
	 * Load bean definitions from the specified resource.
	 *
	 * 从指定resource 加载 bean 定义
	 * @param resource the resource descriptor
	 * @return the number of bean definitions found
	 * @throws BeanDefinitionStoreException in case of loading or parsing errors
	 */
	int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException;

	/**
	 * Load bean definitions from the specified resources.
	 * @param resources the resource descriptors
	 * @return the number of bean definitions found
	 * @throws BeanDefinitionStoreException in case of loading or parsing errors
	 */
	int loadBeanDefinitions(Resource... resources) throws BeanDefinitionStoreException;

	/**
	 * Load bean definitions from the specified resource location.
	 * <p>The location can also be a location pattern, provided that the
	 * {@link ResourceLoader} of this bean definition reader is a
	 * {@code ResourcePatternResolver}.
	 * @param location the resource location, to be loaded with the {@code ResourceLoader}
	 * (or {@code ResourcePatternResolver}) of this bean definition reader
	 *
	 * 从指定的资源位置加载 bean 定义。
	 * <p>位置也可以是位置 pattern，前提是此 bean 定义阅读器的 {@link ResourceLoader}
	 * 是 {@code ResourcePatternResolver}。
	 *
	 *
	 * @return the number of bean definitions found
	 * @throws BeanDefinitionStoreException in case of loading or parsing errors
	 * @see #getResourceLoader()
	 * @see #loadBeanDefinitions(org.springframework.core.io.Resource)
	 * @see #loadBeanDefinitions(org.springframework.core.io.Resource[])
	 */
	int loadBeanDefinitions(String location) throws BeanDefinitionStoreException;

	/**
	 * Load bean definitions from the specified resource locations.
	 * @param locations the resource locations, to be loaded with the {@code ResourceLoader}
	 * (or {@code ResourcePatternResolver}) of this bean definition reader
	 * @return the number of bean definitions found
	 * @throws BeanDefinitionStoreException in case of loading or parsing errors
	 */
	int loadBeanDefinitions(String... locations) throws BeanDefinitionStoreException;

}
